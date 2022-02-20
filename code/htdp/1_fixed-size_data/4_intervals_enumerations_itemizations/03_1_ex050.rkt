;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.3 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Enumerations
;; Exercise 50

#lang htdp/bsl

;; traffic-light-next: TrafficLight -> TrafficLight
;; Yields the next state given current state s.
(check-expect (traffic-light-next "red") "green")
(check-expect (traffic-light-next "green") "yellow")
(check-expect (traffic-light-next "yellow") "red")
(define (traffic-light-next s)
  (cond
    [(string=? "red" s) "green"]
    [(string=? "green" s) "yellow"]
    [(string=? "yellow" s) "red"]))
