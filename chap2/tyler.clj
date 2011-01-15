(ns Chap2-1

  [:use [clojure.test :only [deftest is are run-tests]]])

(def zero (fn [f] identity))

(def one identity)

(def two (fn [f] (comp f f)))

(def three (fn [f] (comp f f f)))

(defn churchToNum [n] ((n inc) 0))

(defn add1 [n] (fn [f] (comp f (n f))))

(deftest test-add1

  (are [x y] (= x (churchToNum y))

       0 zero

       1 one

       2 two

       3 three

       1 (add1 zero)

       2 (add1 one)

       3 (add1 two)

       4 (add1 three)))

(defn -+- [m n] (fn [f] (comp (m f) (n f))))

(defn -*- [m n] (comp m n))

(defn -**- [m n] (n m))

(deftest test-arith

  (are [x y] (= x (churchToNum y))

       3 (-+- zero three)

       0 (-*- zero three)

       3 (-*- one three)

       0 (-**- zero three)

       1 (-**- three zero)

       19 (-+- (-*- (-**- three two) two) one)))

(run-tests)
