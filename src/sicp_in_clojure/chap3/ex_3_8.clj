(ns sicp-in-clojure.chap3.ex-3-8)

(def a (atom 0))

(defn f[x]
  (let [b (* x @a)]
    (reset! a x)
    b))

(+ (f 0) (f 1))
