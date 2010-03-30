(ns sicp-in-clojure.chap1.ex-1-5)

(defn p[] (p))

(defn my-test [x y]
  (if (= x 0) 0 y))

;Then he evaluates the expression
(my-test 0 (p)) ;Stack overflow in clojure

;fully expand and then reduce / normal-order evaluation
(if (= x 0) 0 y)
(if (= 0 0) 0 (p))
(if true 0 (p))
0

;evaluate the arguments and then apply / applicative-order evaluation
(if (= x 0) 0 y)
(if (= 0 0) 0 (p))
(if (= 0 0) 0 ((p)))
(if (= 0 0) 0 (((p)))
;infinite??

;We can conclude that clojure uses applicative-order evaluation 