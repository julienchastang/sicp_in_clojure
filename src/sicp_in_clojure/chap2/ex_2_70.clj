(ns sicp-in-clojure.chap2.ex-2-70
  (:use [sicp-in-clojure.chap2.ex-2-68 :only [encode]])
  (:use [sicp-in-clojure.chap2.ex-2-69 :only [generate-huffman-tree]])  )

(def pairs (reverse (sort-by second '(( A 2) ( NA 16) ( BOOM 1) (SHA 3) (GET 2) (YIP 9) (JOB 2) (WAH 1)))))

(generate-huffman-tree pairs)

;; 3 bits are required for the encoding, log2(8)

(def song 
  '(GET A JOB
       SHA NA NA NA NA NA NA NA NA
       GET A JOB
       SHA NA NA NA NA NA NA NA NA
       WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP
       SHA BOOM))

(count  (encode song (generate-huffman-tree pairs)))

;; 87 bits are required to encode that song

(defn mylog [n b]
  (/ (java.lang.Math/log n )
     (java.lang.Math/log b )))

(* (count song) (mylog (count pairs) 2))

;; With fixed encoding this would take 108 bits.