(ns alphabet-cipher.coder)

(defn alphabet []
  (map char (range (int \a) (+ (int \z) 1))))

(defn pos [c]
  (- (int c) (int \a)))

(defn chart [r c]
  (nth (alphabet) (mod (+ (pos r) (pos c)) 26)))

(defn padded-keyword [k m]
  (subs
    (apply str (concat (repeat (/ (count m) (count k)) k)))
    0
    (count m)))

(defn encode [keyword message]
  (apply str
    (map chart (seq (padded-keyword keyword message)) (seq message))))

(defn decode [keyword message]
  "decodeme")

(defn decypher [cypher message]
  "decypherme")

