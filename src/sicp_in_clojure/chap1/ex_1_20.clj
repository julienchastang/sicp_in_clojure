(ns sicp-in-clojure.chap1.ex-1-20
  (:use clojure.contrib.math))

;counter is global
(def counter (ref 0))

(defn my-gcd [a b]
  (do (dosync (alter  counter inc))
      (if (= b 0)
	a
	(my-gcd b (mod a b)))))

(my-gcd 206 40)

(dosync (ref-set  counter 0))
(my-gcd 206 40)
@counter

;Utililty to expand to normal-order evaluation.
(defmacro gcd-macro
    [a b]
    (list 'if (list '= b 0) a
	  (list 'my-gcd b (list 'mod a b))))

; fully expand and then reduce / normal-order evaluation
(macroexpand-1 '(gcd-macro 206 40))
(if (= 40 0) 206 (my-gcd 40 (mod 206 40)))

(macroexpand-1 '(gcd-macro 40 (mod 206 40)))
(if (= (mod 206 40) 0) 40 (my-gcd (mod 206 40) (mod 40 (mod 206 40))))

(macroexpand-1 '(gcd-macro (mod 206 40) (mod 40 (mod 206 40))))
(if (= (mod 40 (mod 206 40)) 0) (mod 206 40) (my-gcd (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))))

(macroexpand-1 '(gcd-macro  (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))))
(if (= (mod (mod 206 40) (mod 40 (mod 206 40))) 0) (mod 40 (mod 206 40)) (my-gcd (mod (mod 206 40) (mod 40 (mod 206 40))) (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40))))))

(macroexpand-1 '(gcd-macro (mod (mod 206 40) (mod 40 (mod 206 40))) (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40))))))
(if (= (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))) 0) ;<--- evaluates true, mod called 7 times
  (mod (mod 206 40) (mod 40 (mod 206 40))) ; mod called 4 more times 
  (my-gcd (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))) (mod (mod (mod 206 40) (mod 40 (mod 206 40))) (mod (mod 40 (mod 206 40)) (mod (mod 206 40) (mod 40 (mod 206 40)))))))

;For normal order evaluation, mod is called 11 times.

;Utililty to expand to applicative-order evaluation.
(defmacro gcd-macro-2
    [a b]
    (list 'if (list '= b 0) a
	  (list 'my-gcd b (mod a b))))

; evaluate the arguments and then apply / applicative-order evaluation
(macroexpand-1 '(gcd-macro-2 206 40))
(if (= 40 0) 206 (my-gcd 40 6))

(macroexpand-1 '(gcd-macro-2 40 6))
(if (= 6 0) 40 (my-gcd 6 4))

(macroexpand-1 '(gcd-macro-2 6 4))
(if (= 4 0) 6 (my-gcd 4 2))

(macroexpand-1 '(gcd-macro-2 4 2))
(if (= 2 0) 4 (my-gcd 2 0))

(macroexpand-1 '(gcd-macro 2 0))
(if (= 0 0) 2 )

;For applicative order evaluation, mod is called 4 times.
