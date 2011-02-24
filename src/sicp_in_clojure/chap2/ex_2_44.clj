(ns sicp-in-clojure.chap2.ex-2-44
  (:use clojure.contrib.repl-utils)
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))

(defn draw [segs]
  (let [my-doc (Document.)
	writer (PdfWriter/getInstance my-doc
				      (FileOutputStream.
				       "/tmp/draw.pdf"))
	cb (do (.open my-doc)
	       (.getDirectContent writer))]
    (try (doall (map (fn [[x0 y0 x1 y1]]
		       (do (.moveTo cb x0 y0)
			   (.lineTo cb x1 y1))) segs))
	 (.stroke cb)
	 (finally (.close my-doc)))))

;; (def segments (atom []))

;; (defn make-segment [[x0 y0 x1 y1]]
;;   (swap! segments conj [x0 y0 x1 y1]))


(defn- op-segments[f segs x-scale y-scale]
  (map (fn [[x0 y0 x1 y1]]
	 [(f x-scale x0)
	  (f y-scale y0)
	  (f x-scale x1)
	  (f y-scale y1)]) segs))

(defn scale-segments[segs x-scale y-scale]
  (op-segments * segs x-scale y-scale))

(defn translate-segments[segs x-trans y-trans]
  (op-segments + segs x-trans y-trans))
  
;; wave coords from http://xavieran.com/2010/04/24/sicp-section-2-2-4/
(def wave [[0.13 0.6  0.3 0.66]
	   [0.3 0.66  0.4 0.66]
	   [0.4 0.66  0.36 0.84]
	   [0.36 0.84  0.4 1]
	   [0.6 1  0.64 0.84]
	   [0.64 0.84  0.6 0.66]
	   [0.6 0.66  0.71 0.66]
	   [0.71 0.66  1 0.34]
	   [1 0.16  0.6 0.44]
	   [0.6 0.44  0.74 0]
	   [0.6 0  0.5 0.3]
	   [0.5 0.3  0.4 0]
	   [0.24 0  0.34 0.5]
	   [0.34 0.5  0.3 0.6]
	   [0.3 0.6  0.14 0.4]
	   [0.14 0.4  0 0.64]])

(defn beside [p1 p2]
  (concat (scale-segments p1 0.5 1)
	  (translate-segments (scale-segments p2 0.5 1) 0.5 0)))

(defn below [p1 p2]
  (concat (translate-segments (scale-segments p1 1 0.5) 0 0.5)
	  (scale-segments p2 1 0.5)))

(defn flip-vert [p]
  (translate-segments (scale-segments p 1 -1) 0 1))

(defn flipped-pairs [p]
  (let [painter2  (beside p (flip-vert p))]
    (below painter2 painter2)))

(defn right-split [painter n]
  (if (= n 0)
    painter
    (let [smaller (right-split painter (dec n))]
      (beside painter (below smaller smaller)))))

(defn up-split [painter n]
  (if (= n 0)
    painter
    (let [smaller (up-split painter (dec n))]
      (below (beside smaller smaller) painter))))

(defn corner-split [painter n]
  (if (= n 0)
    painter
    (let [up (up-split painter (dec n))
	  right (right-split painter (dec n))]
      (let [top-left (beside up up)
	    bottom-right (below right right)
	    corner (corner-split painter (dec n))]
	(beside (below top-left painter)
		(below corner bottom-right))))))

(def wave2 (beside wave (flip-vert wave)))

(def wave4 (flipped-pairs wave))

(def wave6 (corner-split wave 6))

(def a (flip-vert wave))

(def wave-segments (scale-segments wave6 500 500) )

(draw wave-segments)









