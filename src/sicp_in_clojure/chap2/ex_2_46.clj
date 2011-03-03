(ns sicp-in-clojure.chap2.ex-2-46
    (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))


(defn make-vect [x y] [x y])

(defn xcor-vect [v] (first v))

(defn ycor-vect [v] (last v))

(defn add-vect [v1 v2] (map + v1 v2))

(defn sub-vect [v1 v2] (map - v1 v2))

(defn scale-vect [s v] (map #(* % s) v))

(defn make-frame [v1 v2] ([v1 v2]))

(defn edge1-frame [f] (first f))

(defn edge2-frame [f] (last f))


(defn frame-coord-map [frame]
  (fn [v]
    (add-vect (scale-vect (xcor-vect v)
			  (edge1-frame frame))
	      (scale-vect (ycor-vect v)
			  (edge2-frame frame)))))
