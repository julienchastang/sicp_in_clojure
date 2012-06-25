(ns sicp-in-clojure.chap3.ex-3-2)

(defn make-monitored [f]
  (let [cnt (atom 0)]
    (fn [& args]
      (let [a (first args)]
        (cond (= a 'how-many-calls?) @cnt
              (= a 'reset-count) (reset! cnt 0)
              :else (do (swap! cnt inc)
                        (apply f args)))))))
