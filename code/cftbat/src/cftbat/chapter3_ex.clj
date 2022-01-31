(ns cftbat.chapter3_ex
  (:gen-class))

;; Ex. 1 - Use the str, vector, list, hash-map, and hash-set functions.

(= "Hello world"
   (str "Hello " "world"))

(= [1 2 3]
   (vector 1 2 3))

(= '(1 2 3)
   (list 1 2 3))

(= {:a 1 :b 2}
   (hash-map :a 1 :b 2))

(= #{1 2 3}
   (hash-set 1 1 2 2 3 3 3))

;; Ex. 2 - Write a function that takes a number and adds 100 to it.

(defn add-100
  [n]
  (+ n 100))


;; Ex. 3 - Write a function, dec-maker, that works exactly like the function
;; inc-maker except with subtraction:
;;
;; (def dec9 (dec-maker 9))
;; (dec9 10)
;; => 1

(defn dec-maker
  [dec-by]
  (fn [n] (- n dec-by)))

(def dec14 (dec-maker 14))
(dec14 15)

;; Ex. 4 - Write a function, mapset, that works like map except the return
;; value is a set:
;;
;; (mapset inc [1 1 2 2])
;; => #{2 3}

(defn mapset
  [f seq]
  (set (map f seq)))

;; Ex. 5 - Create a function that’s similar to symmetrize-body-parts except
;; that it has to work with weird space aliens with radial symmetry. Instead of
;; two eyes, arms, legs, and so on, they have five.

;; See ex. 6

;; Ex. 6 - Create a function that generalizes symmetrize-body-parts and the
;; function you created in Exercise 5. The new function should take a
;; collection of body parts and the number of matching body parts to add. If
;; you’re completely new to Lisp languages and functional programming, it
;; probably won’t be obvious how to do this. If you get stuck, just move on to
;; the next chapter and revisit the problem later.

(def asym-alien-body-parts
  [{:name "head" :size 3}
   {:name "eye-1" :size 1}
   {:name "ear-1" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "shoulder-1" :size 3}
   {:name "upper-arm-1" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "forearm-1" :size 3}
   {:name "abdomen" :size 6}
   {:name "kidney-1" :size 1}
   {:name "hand-1" :size 2}
   {:name "knee-1" :size 2}
   {:name "thigh-1" :size 4}
   {:name "lower-leg-1" :size 3}
   {:name "achilles-1" :size 1}
   {:name "foot-1" :size 2}])

(defn copy-part
  [part n]
  {:name (clojure.string/replace (:name part) #"-(.*)" (str "-" n))
   :size (:size part)})

(defn match-part
  [part n]
  (reduce (fn [acc n]
            (conj acc (copy-part part n)))
          #{}
          (range 1 (inc n))))

(defn symmetrize-alien-parts
  [asym-alien-parts n]
  (reduce (fn [acc curr]
            (into acc (match-part curr n)))
          []
          asym-alien-body-parts))
