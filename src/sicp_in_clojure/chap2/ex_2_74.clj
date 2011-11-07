(ns sicp-in-clojure.chap2.ex-2-74
  (:use [clojure.repl])
  (:refer-clojure :exclude [get]))

;; Cheating somewhat by relying on clojure maps and sets

(defn pair? [c]
  (and (seq? c) (> (count c) 1)))

;; Type operations

(defn attach-tag [type-tag contents]
  (cons type-tag contents))

(defn type-tag [datum]
  ( if (pair? datum)
    (first datum)
    (throw (Exception. (str "Bad tagged datum -- TYPE-TAG " datum)))))

(defn contents [datum]
  (if (pair? datum)
    (rest datum)
    (throw (Exception. (str "Bad tagged datum -- CONTENTS " datum)))))

;; Dynamic dispatch map

(def op-map (atom {}))

(defn put [op type item]
  (swap! op-map assoc (list op type) item))

(defn get [op type]
  (@op-map (list op type)))

(defn apply-generic [op & args]
  (let [type-tags (map type-tag args)]
    (let [proc (get op type-tags)]
      (if proc
        (apply proc (map contents args))
        (throw (Exception.
                (str "No method for these types -- APPLY-GENERIC " op " " type-tags)))))))

;; Packages

(defn install-div1-package []
  (let [get-salary (fn [rec] (-> rec rest first first))]
    (put 'get-salary '(div1) get-salary)))

(defn install-div2-package []
  (let [get-salary (fn [rec] (-> rec rest first rest first))]
    (put 'get-salary '(div2) get-salary)))

(install-div1-package)
(install-div2-package)

;; operations
(defn get-record [emp-file name]
  (clojure.core/get emp-file name))

(defn get-salary [rec] (apply-generic 'get-salary rec))


(defn find-employee-record [name & emp-files]
  (letfn [(f [emp-file]
            (first (filter #(= (first (contents %)) name) emp-file)))]
      (some f emp-files)))

;; Employee Records

(def employees-div1
  (map #(attach-tag 'div1 %)
       '(("Joe" (60 "Main Street"))
         ("Bob" (70 "Pine Stree"))
         ("Fred" (80 "Broadway")))))

(def employees-div2
  (map #(attach-tag 'div2 %)
       '(("Sal" ("Main Street" 60))
         ("Vince" ("Pine Stree" 70))
         ("Tony" ("Broadway" 80)))))


(def sal (find-employee-record "Sal" employees-div1 employees-div2))

(def bob (find-employee-record "Bob" employees-div1 employees-div2))
;; (get-sala)
