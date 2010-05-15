(ns sicp-in-clojure.chap1.ex-2-20
  (:use clojure.contrib.repl-utils))

; cheating a bit by using advanced clojure features.
(defn same-parity[& c]
  (if (even? (first c)) (filter #(even? %) c)
			       (filter #(odd? %) c)))

(same-parity 1 2 3 4 5 6 7)
; (1 3 5 7)
(same-parity 2 3 4 5 6 7)
; (2 4 6)
