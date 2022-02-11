;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.5 Fixed-Size Data / Adding Structure / Computing with Structures
;; ex. 70

#lang htdp/bsl

(define-struct centry [name home office cell])

;; (centry-name (make-centry n h o c)) == n
;; (centry-home (make-centry n h o c)) == h
;; (centry-office (make-centry n h o c)) == o
;; (centry-cell (make-centry n h o c)) == c

(define-struct phone [area number])

;; (phone-area (make-phone a n)) == a
;; (phone-number (make-phone a n)) == n

(phone-area
 (centry-office
  (make-centry
   "Shriram Fisler"
   (make-phone 207 "363-2421")
   (make-phone 101 "776-1099")
   (make-phone 208 "112-9981"))))

;; Step 1:
;; (centry-office
;;  (make-centry
;;   "Shriram Fisler"
;;   (make-phone 207 "363-2421")
;;   (make-phone 101 "776-1099")
;;   (make-phone 208 "112-9981")))

;; Law: (centry-office (make-centry n h o c)) == o
;; Eval: (make-phone 101 "776-1099")

;; Step 2:
;; (phone-area (make-phone 101 "776-1099"))

;; Law: (phone-area (make-phone a n)) == a
;; Eval: 101

;; Stepper: OK!
