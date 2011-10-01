(ns sicp-in-clojure.chap2.ex-2-36
  (:use clojure.contrib.repl-utils))

(defn accumulate [op initial sequence]
  (if (empty? sequence) initial
      (op (first sequence)
	  (accumulate op initial (rest sequence)))))

;; Return the car of each list.
(defn fseq [seqs]
  (if (empty? seqs) nil
      (cons (ffirst seqs)
	    (fseq (rest seqs)))))

;; Return the cdr of each list
(defn rsseq [seqs]
  (if (empty? seqs) (rest seqs)
      (cons (rest (first seqs)) 
	(rsseq (rest seqs)))))

(defn accumulate-n [op initial seqs]
  (if (empty? (first seqs)) nil
      (cons (accumulate op initial (fseq seqs))
	    (accumulate-n op initial (rsseq seqs)))))

;; Better soln using map function
(defn accumulate-n [op initial seqs]
  (if (empty? (first seqs)) nil
      (cons (accumulate op initial (map first seqs))
	    (accumulate-n op initial (map rest seqs)))))

(def a [[1 2 3] [4 5 6] [7 8 9]])

(accumulate-n + 0 a)
