(ns sicp-in-clojure.chap2.ex-2-52b
  (:use [sicp-in-clojure.chap2.ex-2-44 :exclude [corner-split square-limit]])
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))

(defn corner-split [painter n]
  (if (= n 0)
    painter
    (let [up (up-split painter (dec n))
	  right (right-split painter (dec n))]
      (let [top-left up
	    bottom-right right
	    corner (corner-split painter (dec n))]
	(beside (below top-left painter)
		(below corner bottom-right))))))

(def w (corner-split wave 6))

(def ws (scale-segments w 500 500) )

(draw ws)
