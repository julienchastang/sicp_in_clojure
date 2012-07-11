(ns sicp-in-clojure.chap3.ex-3-5)

(defn sqrt [x] (Math/sqrt x))

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (mod a b))))

(declare monte-carlo cesaro-test)

(defn estimate-pi [trials]
  (sqrt (/ 6 (monte-carlo trials cesaro-test))))


(defn rnd [] (rand-int 1000))

(defn cesaro-test []
  (= (gcd (rnd) (rnd)) 1))

(defn monte-carlo [trials experiment]
  (let [iter (fn [trials-remaining trials-passed ]
               (cond
                 (= trials-remaining 0) (/ trials-passed trials)
                 (experiment) (recur (- trials-remaining 1) (+ trials-passed 1))
                 :else (recur (- trials-remaining 1) trials-passed)))]
    (iter trials 0)))


;; (estimate-pi 20000)
;; (estimate-pi 20000)

(defn random-in-range [low high]
  (let [range (- high low)]
        (+ low (rand range))))


(defn my-p [x y]
  (<= (+ (java.lang.Math/pow (- x 5) 2)
         (java.lang.Math/pow (- y 7) 2))
      9))

(defn my-p [x y]
  (<= (+ (java.lang.Math/pow  x 2)
         (java.lang.Math/pow  y 2))
      1))

(defn estimate-integral [p x1 x2 y1 y2 trials]
  (let [prd (fn [] (p (random-in-range x1 x2) (random-in-range y1 y2)))]
    (monte-carlo trials prd)))


;; (defn rand-update [x] (rnd))

;; (defn estimate-pi [trials]
;;   (sqrt (/ 6 (monte-carlo trials random-init))))

;; (defn random-gcd-test [trials initial-x]
;;   (let [iter (fn [trials-remaining trials-passed x]
;;                (let [x1 (rand-update x)]
;;                  (let [x2 (rand-update x1)]
;;                    (cond
;;                      (= trials-remaining 0) (/ trials-passed trials)
;;                      (= (gcd x1 x2) 1)
;;                      (recur (- trials-remaining 1) (+ trials-passed 1) x2)
;;                      :else
;;                      (recur (- trials-remaining 1) trials-passed x2)))))]
;;     (iter trials 0 initial-x)))

;; (defn rand-in-range [low high]
;;   (+ low (rand-int (- high low))))
