(ns sicp-in-clojure.chap3.ex-3-1)


;; (def balance (atom 100))

;; (defn withdraw [amount]
;;   (if (>= @balance amount)
;;     (swap! balance - amount)
;;     (throw (Exception. "Insufficient funds"))))

;; (def new-withdraw
;;   (let [balance 100]
;;     (fn [amount]
;;       (if (>= @balance amount)
;;         (swap! balance - amount)
;;         (throw (Exception. "Insufficient funds"))))))


;; (defn make-withdraw [balance]
;;   (fn [amount]
;;     (if (>= @balance amount)
;;       (swap! balance - amount)
;;       (throw (Exception. "Insufficient funds")))))

(defn make-account [balance]
  {:withdraw
   (fn [amount]
     (if (>= @balance amount)
       (swap! balance - amount)
       (throw (Exception. "Insufficient funds"))))
   :deposit
   (fn [amount]
     (swap! balance + amount))})
