(ns sicp-in-clojure.chap2.ex-2-52c
  (:use [sicp-in-clojure.chap2.ex-2-45 :exclude [square-limit]])
  (:use [sicp-in-clojure.chap2.ex-2-44 :only [wave scale-segments draw flip-vert flip-horiz corner-split]])
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))

(defn square-limit [painter n]
  (let [combine4 (square-of-four  rotate180 flip-horiz
				  flip-vert identity)]
    (combine4 (corner-split painter n))))

(def w (square-limit wave 6))

(def ws (scale-segments w 500 500) )

(draw ws)
