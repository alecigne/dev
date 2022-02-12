;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.7 Fixed-Size Data / Adding Structure / The Universe of Data
;; ex. 77

#lang htdp/bsl

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
