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

(defn ^:private make-account-helper [balance]
  {:withdraw
   (fn [amount]
     (if (>= @balance amount)
       (swap! balance - amount)
       (throw (Exception. "Insufficient funds"))))
   :deposit
   (fn [amount]
     (swap! balance + amount))})

(defn make-account [balance]
  (make-account-helper (atom balance)))

;; (def a (make-account 73))

;; ;; almost OO style :-)

;; ((a :withdraw) 11)

;; ((a :deposit) 12)


(defn make-accumulator [n]
  (let [numb (atom n)]
    (fn [num] (swap! numb +  num))))
