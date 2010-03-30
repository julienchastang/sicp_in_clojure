(ns sicp-in-clojure.chap1.ex-1-4)

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

(a-plus-abs-b 2 2)  ;4
(a-plus-abs-b 2 -1) ;3

;if b > 0 the + function will be returned, a and b will be added together.
;else - function will be return and b will be subtracted from a (but b
;is negative -- or zero -- so a and -b will be added yielding a positive 
;number.)