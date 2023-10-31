(ns solution2
  (:require
    [clojure.pprint :as pp]
    [clojure.java.io :as io]
    [clojure.spec.alpha :as s]
    [invoice-spec :as spec]
    [cheshire.core :refer :all]
    ))

(defn write-to-edn-file [data file-path]
  (with-open [writer (io/writer file-path)]
    (binding [*out* writer]
      (pp/write data))))

(defn read-json-from-file [file-path]
  (slurp file-path))

(defn readjson [filepath]
  (let [json-string (read-json-from-file filepath)
        cli-map (parse-string json-string true)]
    (print (s/valid? ::spec/invoice cli-map))
    (write-to-edn-file cli-map "result_final.edn")
    )
  )

;; Example usage
(readjson "invoice.json")