(ns sicp-in-clojure.chap2.ex-2-47)

(defn make-frame [origin edge1 edge2]
  (list origin edge1 edge2))

(defn edge1-frame [f] (second f))

(defn edge2-frame [f] (last f))

(defn origin-frame [f] (first f))

(defn make-frame [origin edge1 edge2]
  (cons origin
	(vector (cons edge1
		      (vector edge2)))))

(defn edge1-frame [f] (first (second f)))

(defn edge2-frame [f] (last (second f)))

(defn origin-frame [f] (first f))


