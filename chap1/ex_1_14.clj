(ns sicp-in-clojure.chap1.ex-1-14
  (:use clojure.contrib.repl-utils))

(defn first-denomination [kinds-of-coins]
  (let [m {1 1 2 5 3 10 4 25 5 50}]
    (m kinds-of-coins)))

;counter is global
(def counter (ref 0))

(defn cc[amount kinds-of-coins]
;  (print amount " "  kinds-of-coins "| ")
  (dosync (alter  counter inc))
  (cond (= amount 0) 1
	(or (< amount 0) (= kinds-of-coins 0)) 0
	:else (+ (cc amount
		     (dec kinds-of-coins))
		 (cc (- amount
			(first-denomination kinds-of-coins))
		     kinds-of-coins))))

;Computes only next level. Useful for generating diagram.
(defn cc-1[amount kinds-of-coins]
  (cond (= amount 0) 1
	(or (< amount 0) (= kinds-of-coins 0)) 0
        :else (println amount
		       (dec kinds-of-coins)
		       (- amount (first-denomination kinds-of-coins))
		       kinds-of-coins)))

;Returns the change along w/ how many times cc was called.
(defn count-change[amount]
  (do (dosync (ref-set  counter 0))
      [(cc amount 5) (deref counter)]))

(count-change 100)
(count-change 11)

;For drawing tree
(cc-1 1 2)
(cc-1 0 1)

;The tree can be found here:
; http://github.com/julienchastang/sicp_in_clojure/blob/master/chap1/ex_1_14.png
;


; orders of growth of the space and number of 
; steps used by this process as the amount to
; be changed increases? 

; For space, same as fibo, n.
;
; For steps, I do not know.
; Could perhaps determine empirically.
; The tree drawing does not help.