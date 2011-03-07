(ns sicp-in-clojure.chap2.ex-2-50
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

(defn flip-vert [painter]
  (transform-painter painter
		     [0 1]
		     [1 1]
		     [0 0]))


(defn flip-vert [painter]
  (transform-painter painter
		     [0.5 0.5]
		     [1.0 1.0]
		     [0.5 1.0]))


(defn rotate90 [painter]
  (transform-painter painter
		     [1.0 0.0]
		     [1.0 1.0]
		     [0.0 1.0]))


(def a ((rotate90 (segments->painter wave))
	(make-frame [100 100] [200 30] [30 200])))

(draw a)



