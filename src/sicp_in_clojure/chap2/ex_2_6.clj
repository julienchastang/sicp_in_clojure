(ns sicp-in-clojure.chap1.ex-2-6
  (:use clojure.contrib.math))

(def zero (fn [f] identity))

(def one identity)

(def two (fn [f] (comp f f)))

(def three (fn [f] (comp f f f)))

(defn church-num [n] ((n inc) 0))

(defn add-1[n]
    (fn[f] (fn[x] (f ((n f) x)))))

(church-num zero) ;0

(church-num one) ;1

(church-num two) ;2

(church-num three) ;3

(church-num (add-1 zero)) ;1











