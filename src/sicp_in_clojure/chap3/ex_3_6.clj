(ns sicp-in-clojure.chap3.ex-3-6)


(def rnd-help (java.util.Random. 0))

(defn rnd [a]
  (cond (= a 'generate) (.nextInt rnd-help)
        (= a 'reset) (.setSeed rnd-help 0)
        :else nil))
