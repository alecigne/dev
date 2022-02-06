;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.2 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Computing Conditionally
;; Exercise 48

#lang htdp/bsl

(define (reward s)
  (cond
    [(<= 0 s 10) "bronze"]
    [(and (< 10 s) (<= s 20)) "silver"]
    [else "gold"]))

;; 1
(reward 18)

;; 2
(cond
  [(<= 0 18 10) "bronze"]
  [(and (< 10 18) (<= 18 20)) "silver"]
  [else "gold"])

;; 3
(cond
  [#false "bronze"]
  [(and (< 10 18) (<= 18 20)) "silver"]
  [else "gold"])

;; 4
(cond
  [(and (< 10 18) (<= 18 20)) "silver"]
  [else "gold"])

;; 5
(cond
  [(and #true (<= 18 20)) "silver"]
  [else "gold"])

;; 6
(cond
  [(and #true #true) "silver"]
  [else "gold"])

;; 7
(cond
  [#true "silver"]
  [else "gold"])

;; 8
"silver"
