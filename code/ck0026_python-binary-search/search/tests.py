import unittest
from . import binary_search_recursive
from . import binary_search_iterative


class TestSuite(unittest.TestCase):

    array = [1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 6]

    def test_binary_search_iterative(self):
        self.assertEqual(10, binary_search_iterative.search(self.__class__.array, 5))
        self.assertEqual(11, binary_search_iterative.search(self.__class__.array, 6))
        self.assertEqual(-1, binary_search_iterative.search(self.__class__.array, 7))
        self.assertEqual(-1, binary_search_iterative.search(self.__class__.array, -1))

    def test_binary_search_recursive(self):
        self.assertEqual(10, binary_search_recursive.search(self.__class__.array, 5))
        self.assertEqual(11, binary_search_recursive.search(self.__class__.array, 6))
        self.assertEqual(-1, binary_search_recursive.search(self.__class__.array, 7))
        self.assertEqual(-1, binary_search_recursive.search(self.__class__.array, -1))


if __name__ == '__main__':
    unittest.main()
