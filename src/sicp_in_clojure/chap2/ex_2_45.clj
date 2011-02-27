(ns sicp-in-clojure.chap2.ex-2-45
  (:use [sicp-in-clojure.chap2.ex-2-44 :exclude (flipped-pairs
						 wave4
						 wave-segments)])
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

(def wave4 (flipped-pairs wave))

(def wave-segments (scale-segments wave4 500 500) )

(draw wave-segments)
