(ns alphabet-cipher.coder)

(defn alphabet []
  (map char (range (int \a) (+ (int \z) 1))))

(defn pos [c]
  (- (int c) (int \a)))

(defn en-chart [c r]
  (nth (alphabet) (mod (+ (pos r) (pos c)) 26)))

(defn de-chart [c r]
  (nth (alphabet) (mod (- (pos r) (pos c)) 26)))

(defn dec-chart [c r]
  (nth (alphabet) (mod (- (pos c) (pos r)) 26)))

(defn r-take [s n]
  (take n s))

(defn pad [string n]
  (subs (apply str (concat (repeat (/ n (count string)) string))) 0 n))

(defn generator [s]
  (take 1
    (filter
      (fn [x] (= (pad x (count s)) (apply str s)))
      (map
        (partial apply str)
        (map
          (partial r-take s)
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
