(ns solution3
  (:require
    [clojure.test :refer [deftest is run-tests]]
    [clojure.test :refer :all]
    [invoice-item :as invoice]))
(use 'clojure.test)

(deftest test-example
  (testing "subtotal calculation without discount rate"
    (let [precise-quantity 2
          precise-price 50
          discount-rate 0]
      (is (= 100 (invoice/subtotal1 precise-quantity precise-price discount-rate)))))

  (testing "subtotal calculation with negative price"
    (let [precise-quantity 1000000000
          precise-price 999999999
          discount-rate 10]
      (is (= 899999999000000000 (invoice/subtotal1 precise-quantity precise-price discount-rate)))))

  (testing "subtotal calculation with negative price"
    (let [precise-quantity 1000000000
          precise-price 999999999
          discount-rate 10]
      (is (= 899999999000000000 (invoice/subtotal1 precise-quantity precise-price discount-rate)))))


  (testing "subtotal calculation with quantity invalid"
    (let [precise-quantity 500
          precise-price 100
          discount-rate 1]
      (try
        (invoice/subtotal1 precise-quantity precise-price discount-rate)
        (catch ClassCastException e
          (is (= "Invalid input: negative quantity" (.getMessage e)))))))

  )

(run-tests)
