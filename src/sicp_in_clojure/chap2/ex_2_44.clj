(ns sicp-in-clojure.chap2.ex-2-44
  (:use clojure.contrib.repl-utils)
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))

(def my-doc (Document.))

(def writer (PdfWriter/getInstance my-doc
				   (FileOutputStream.
				    "/tmp/Helloworld.pdf")))

(.open my-doc)

(def cb (.getDirectContent writer))


(.moveTo cb 50 400) 

(make-segment [100 100] [200 200])
(make-segment [100 50] [200 200])


(.stroke cb)
(.close my-doc)


(def segments (atom []))


(defn make-segment [[x0 y0] [x1 y1]]
  (swap! segments conj [x0 y0 x1 y1]))


(defn draw [segs]
  (let [my-doc (Document.)
	writer (PdfWriter/getInstance my-doc
				      (FileOutputStream.
				       "/tmp/Helloworld.pdf"))
	cb (do (.open my-doc)
	       (.getDirectContent writer))]
    (doall (map (fn [[x0 y0 x1 y1]]
		  (do (.moveTo cb x0 y0)
		      (.lineTo cb x1 y1))) segs))
    (.stroke cb)
    (.close my-doc)))




