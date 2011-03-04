(ns sicp-in-clojure.chap2.ex-2-46)


(defn make-frame [origin edge1 edge2]
  (list origin edge1 edge2))

(defn edge1-frame [f] (second f))

(defn edge2-frame [f] (last f))

(defn origin-frame [f] (first f))


(defn make-vect [x y] [x y])

(defn xcor-vect [v] (first v))

(defn ycor-vect [v] (last v))

(defn add-vect [v1 v2] (map + v1 v2))

(defn sub-vect [v1 v2] (map - v1 v2))

(defn scale-vect [s v] (map #(* % s) v))


(defn frame-coord-map [frame]
  (fn [v]
    (add-vect
     (origin-frame frame)
     (add-vect (scale-vect (xcor-vect v)
					  (edge1-frame frame))
			      (scale-vect (ycor-vect v)
					  (edge2-frame frame))))))



