(ns sicp-in-clojure.chap2.ex-2-51
  (:use [sicp-in-clojure.chap2.ex-2-46]
	[sicp-in-clojure.chap2.ex-2-48])
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))


(defn transform-painter [painter origin corner1 corner2]
  (fn [frame]
    (let [m (frame-coord-map frame)]
      (let [new-origin (m origin)]
	(painter
	 (make-frame new-origin
		     (sub-vect (m corner1) new-origin)
		     (sub-vect (m corner2) new-origin)))))))


(defn beside [painter1 painter2]
  (let [split-point [0.5 0]]
    (let [paint-left
	  (transform-painter painter1
			     [0 0]
			     split-point
			     [0 1])
	  paint-right
	  (transform-painter painter2
			     split-point
			     [1 0]
			     [0.5 1])]
      (fn [frame]
	(concat (paint-left frame)
		(paint-right frame))))))

(defn below [painter1 painter2]
  (let [split-point [0 0.5]]
    (let [paint-above
	  (transform-painter painter1
     			     split-point
			     [1 0.5]
			     [0 1])
	  paint-below
	  (transform-painter painter2
			     [0 0]
			     [1 0]
     			     split-point)]
      (fn [frame]
	(concat (paint-above frame)
		(paint-below frame))))))

(def a ((below (segments->painter wave)
		(segments->painter wave))
	(make-frame [100 100] [200 0] [0 200])))

(draw a)
