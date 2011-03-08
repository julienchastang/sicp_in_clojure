(ns sicp-in-clojure.chap2.ex-2-48
  (:use [sicp-in-clojure.chap2.ex-2-46])
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
    (try (doall (map (fn [[[x0 y0] [x1 y1]]] 
		       (do (.moveTo cb x0 y0)
			   (.lineTo cb x1 y1))) segs))
	 (.stroke cb)
	 (finally (.close my-doc)))))

(def wave [[ [0.13 0.6] [0.3 0.66]]
	   [ [0.3 0.66] [0.4 0.66]]
	   [ [0.4 0.66] [0.36 0.84]]
	   [ [0.36 0.84] [0.4 1]]
	   [ [0.6 1] [0.64 0.84]]
	   [ [0.64 0.84] [0.6 0.66]]
	   [ [0.6 0.66] [0.71 0.66]]
	   [ [0.71 0.66] [1 0.34]]
	   [ [1 0.16] [0.6 0.44]]
	   [ [0.6 0.44] [0.74 0]]
	   [ [0.6 0] [0.5 0.3]]
	   [ [0.5 0.3] [0.4 0]]
	   [ [0.24 0] [0.34 0.5]]
	   [ [0.34 0.5] [0.3 0.6]]
	   [ [0.3 0.6] [0.14 0.4]]
	   [ [0.14 0.4] [0 0.64]]])

(defn make-segment [[ x0 y0 x1 y1]]
  [[x0 y0] [x1 y1]])

(defn start-segment[segment]
  (first segment))

(defn end-segment[segment]
  (last segment))


(defn segments->painter [segment-list]
  (fn [frame]
    (map (fn [segment]
	   [((frame-coord-map frame) (start-segment segment))
	    ((frame-coord-map frame) (end-segment segment))])
	 segment-list)))

(defn drawing [d] ((segments->painter d) (make-frame [100 100] [200 30] [30 200])))


(draw (drawing wave))
