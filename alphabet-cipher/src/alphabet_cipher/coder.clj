(ns alphabet-cipher.coder)

(defn alphabet []
  (map char (range (int \a) (+ (int \z) 1))))

(defn pos [c]
  (- (int c) (int \a)))

(defn chart [op a b]
  (nth (alphabet) (mod (op (pos a) (pos b)) (count (alphabet)))))

(defn en-chart [c r]
  (chart + r c))

(defn de-chart [c r]
  (chart - r c))

(defn dec-chart [c r]
  (chart - c r))

(defn pad [string n]
  (subs (apply str (concat (repeat (/ n (count string)) string))) 0 n))

(defn generator [s]
  (take 1
    (filter
      (fn [x] (= (pad x (count s)) (apply str s)))
      (map
        (partial apply str)
        (map
          (fn[x] (take x s))
          (range 1 (+ 1 (count s))))))))

(defn encode [keyword message]
  (apply str
    (map en-chart (seq (pad keyword (count message))) (seq message))))

(defn decode [keyword message]
  (apply str
    (map de-chart (seq (pad keyword (count message))) (seq message))))

(defn decypher [cypher message]
  (apply str
    (generator (map dec-chart (seq cypher) (seq message)))))
