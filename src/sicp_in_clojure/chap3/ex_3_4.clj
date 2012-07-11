(ns sicp-in-clojure.chap3.ex-3-4)


(defn call-the-cops [] "Cops are coming")

(defn ^:private make-account-helper [balance password cnt]
  {:withdraw
   (fn [amount pw]
     (if  (< @cnt  7)
       (if (= pw password)
         (if (>= @balance amount)
           (swap! balance - amount)
           (throw (Exception. "Insufficient funds")))
         (do (swap! cnt inc)
             (throw (Exception. "Incorrect password"))))
       (call-the-cops)))
   :deposit
   (fn [amount pw]
     (if (< @cnt 7)
       (if (= pw password)
         (swap! balance + amount)
         (do  (swap! cnt inc)
              (throw (Exception. "Incorrect password"))))
       (call-the-cops)))})

(defn make-account [balance password]
  (make-account-helper (atom balance) password (atom 0)))
