(ns sicp-in-clojure.chap2.ex-2-49
  (:use [sicp-in-clojure.chap2.ex-2-48])
  (:import (com.lowagie.text Document Paragraph)
	   (com.lowagie.text.pdf PdfWriter PdfContentByte)
	   (java.io FileOutputStream)))

(def a [[[0 0] [0 1]]
	[[0 1] [1 1]]
	[[1 1] [1 0]]
	[[1 0] [0 0]]])

(def b [[[0 0] [0 1]]
	[[0 1] [1 0]]
	[[1 0] [1 1]]
	[[1 1] [0 0]]])


(def c [[[0 0.5] [0.5 1]]
	[[0.5 1] [1 0.5]]
	[[1 0.5] [0.5 0]]
	[[0.5 0] [0 0.5]]])

;; For wave painter, see earlier exercises

(draw (drawing c))
