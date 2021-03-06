(ns structured-data)

(defn do-a-thing [x]
  (let [xx (+ x x)]
  (Math/pow xx xx)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x y z] v]
    (spiff [x y z])))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1)))

(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- y2 y1)))

(defn square? [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (= (- x2 x1) (- y2 y1))))

(defn area [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (* (- x2 x1) (- y2 y1))))

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle
         [a b] point]
    (and (<= x1 a x2) (<= y1 b y2))))

(defn contains-rectangle? [outer inner]
  (let [[a b] inner]
    (and (contains-point? outer a)
 		 (contains-point? outer b))))


(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (> (count(:authors book)) 1))

(defn add-author [book new-author]
  (let [authors (conj (book :authors) new-author)]
    (assoc book :authors authors)))

(defn alive? [author]
	(not (contains? author :death-year)))

(defn element-lengths [collection]
	(map count collection))

(defn second-elements [collection]
	(let [second (fn [item] (get item 1))]
		(map second collection))) 

(defn titles [books]
 	(map :title books))

(defn monotonic? [a-seq]
	(or (apply <= a-seq) (apply >= a-seq)))

(defn stars [n]
	(apply str (repeat n "*")))

(defn toggle [a-set elem]
	(if (contains? a-set elem) 
		(disj a-set elem)
		(conj a-set elem)))

(defn contains-duplicates? [a-seq]
	(if (not= (count a-seq) (count (set a-seq))) true false))

(defn old-book->new-book [book]
 	(assoc book :authors (set (:authors book))))

(defn has-author? [book author]
	(contains? (book :authors) author))

(defn authors [books]
	(apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
	(set (map :name (authors books))))

(defn author->string [author]
	(let [name (:name author)
			years (if (contains? author :birth-year)
                (str " (" (:birth-year author) " - " (:death-year author) ")"))]
        (str name years)))

(defn authors->string [authors]  
	(apply str (interpose ", " (map author->string authors))))

(defn book->string [book]
	(str (:title book) ", written by " (authors->string (:authors book))))

(defn books->string [books]
	(cond
		(= 0 (count books)) "No books."
		(= 1 (count books)) (str "1 book. " (book->string (first books)) ".")
		:else (str (count books) " books. "
			(apply str (interpose ". " (map book->string books))) ".")))

(defn books-by-author [author books]
	(filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
   (first (filter (fn [author] (= name (:name author)))  authors)))

(defn living-authors [authors]
	(filter alive? authors))

(defn has-a-living-author? [book]
	(not (empty? (filter alive? (:authors book)))))

(defn books-by-living-authors [books]
	(filter has-a-living-author? books))

; %________%
