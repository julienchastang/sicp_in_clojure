(ns sicp-in-clojure.chap2.ex-2-23
  (:use clojure.contrib.repl-utils))

; (defn for-eac[f c]
;  (do (println c "foo")
;      (apply f [(first c)])
;      (if (empty? c) nil
;	  (for-eac f (rest c)))))


(defn for-each[f c]
  (if (empty? c) nil
      (do (f (first c))
	  (for-each f (rest c)))))

(defn for-each[f c]
  (if (empty? c) nil
      (do (f (first c)))))
	  ;(for-each f (rest c)))))

(for-each (fn[x] println x)
	  (list 57 321 88))

