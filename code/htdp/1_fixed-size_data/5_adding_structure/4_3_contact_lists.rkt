;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.4 Fixed-Size Data / Adding Structure / Defining Structure Types
;; Contact lists

#lang htdp/bsl

(define-struct contact [name home office cell])
(define-struct phone [area number])

(define contact1
  (make-contact "Shriram Fisler"
                (make-phone 207 "363-2421")
                (make-phone 101 "776-1099")
                (make-phone 208 "112-9981")))

 (phone-number (contact-office contact1))
