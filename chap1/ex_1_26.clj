(ns sicp-in-clojure.chap1.ex-1-26
  (:use clojure.contrib.math))

; Eva Lu Ator is right. Louis Reasoner is unnecessarily computing expmod twice
; at each iteration.