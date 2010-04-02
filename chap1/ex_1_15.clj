(ns sicp-in-clojure.chap1.ex-1-15
  (:use clojure.contrib.repl-utils))

(defn abs[x] (Math/abs x))

(defn cube[x] (* x x x))

;counter is global
(def counter (ref 0))

(defn p[x]
  (do (dosync (alter  counter inc))
  (- (* 3 x)
     (* 4 (cube x)))))

(defn sine[angle]
  (println angle)
  (if (not (> (abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

(dosync (ref-set  counter 0))
(sine 12.15) ;p is called 5 times.
@counter

; Since it is recursive (and not tail recursive)
; and we are dividing by 3 at each iteration,
; space consumption will be proportional to log3 n
; steps will be proportional to log 3 n as well.