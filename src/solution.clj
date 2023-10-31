(ns solution)
(def invoice (clojure.edn/read-string (slurp "invoice.edn")))

(defn validateIVA
  [invoiceitem]
  (some #(= 19 (:tax/rate %)) (-> invoiceitem :taxable/taxes))
  )

(defn validateRetefuente
  [invoiceitem]
  (some #(= 1 (:retention/rate %)) (-> invoiceitem :retentionable/retentions))
  )

(defn filter-invoice-items [invoice]
  (let [items (:invoice/items invoice)
        hasIVA (fn [item] (validateIVA item))
        hasRetefuente (fn [item] (validateRetefuente item))]
    (let [iva-items (->> items
                         (filter (fn [param1] (and (not (hasRetefuente param1)) (hasIVA param1))))
                         )
          retention-items (->> items
                               (filter (fn [param1] (and (not (hasIVA param1)) (hasRetefuente param1))))
                               )]
      (merge iva-items retention-items)
      )))


(print (filter-invoice-items invoice))

