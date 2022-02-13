;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.8 Fixed-Size Data / Adding Structure / Designing with Structures
;; ex. 81

#lang htdp/bsl

;; * Data definitions

;; An Hour is an Integer such that:
;; 0 <= h < 24

;; A Minute is an Integer such that:
;; 0 <= m < 60

;; A Second is an Integer such that:
;; 0 <= s < 60

(define-struct time [hour minute sec])  ; second is taken
;; A Time is a structure:
;; (make-time Hour Minute Second)
;; Interpretation: the local time.

;; * Functions

;; time->seconds: Time -> Number
;; Seconds elapsed since midnight.
(define (time->seconds time)
  (+ (* 3600 (time-hour time))
     (* 60 (time-minute time))
     (time-sec time)))

;; * Tests

(check-expect
 (time->seconds (make-time 0 0 0))
 0)

(check-expect
 (time->seconds (make-time 12 30 2))
 45002)

(check-expect
 (time->seconds (make-time 23 59 59))
 86399)
