(ns sicp-in-clojure.chap3.ex-3-7
  (:use [sicp-in-clojure.chap3.ex-3-4]))

(defn make-joint [account account-passwd joint-passwd]
  (let [pm {joint-passwd account-passwd}]
    {:withdraw (fn [amount pw]
                 ((:withdraw account) amount (pm joint-passwd)))
     :deposit (fn [amount pw]
                ((:deposit account) amount (pm joint-passwd)))}))
