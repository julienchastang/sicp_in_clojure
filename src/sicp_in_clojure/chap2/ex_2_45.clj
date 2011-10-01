(ns sicp-in-clojure.chap2.ex-2-45
  (:use [sicp-in-clojure.chap2.ex-2-44 :exclude (flipped-pairs
						 wave4
						 wave6
						 wave-segments
						 square-limit
						 right-split
						 up-split)])
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))

(defn square-of-four [tl tr bl br]
  (fn [painter]
    (let [top (beside (tl painter) (tr painter))
	  bottom (beside (bl painter) (br painter))]
      (below bottom top))))

(defn flipped-pairs [painter]
  (let [combine4 (square-of-four identity flip-vert
				  identity flip-vert)]
    (combine4 painter)))

(defn rotate180 [painter]
  ((comp flip-vert flip-horiz) painter))

(defn square-limit [painter n]
  (let [combine4 (square-of-four  rotate180 flip-vert
				  flip-horiz identity)]
    (combine4 (corner-split painter n))))

;; (def wave4 (flipped-pairs wave))

;; (def wave6 (square-limit wave 5))

;; (def wave-segments (scale-segments wave6 500 500) )

;; (draw wave-segments)

(defn split [f1 f2]
  (fn [painter n]
    (letfn [(split* [painter n]
		    (if (= n 0)
		      painter
		      (let [smaller (split* painter (dec n))]
			(f1 painter (f2 smaller smaller)))))]
      (split* painter n))))

(defn right-split [painter n]
  ((split beside below) painter n))

(defn up-split [painter n]
  ((split below beside) painter n))

(def my-wave (right-split wave 5))

(def wave-segments (scale-segments my-wave 500 500) )

(draw wave-segments)
