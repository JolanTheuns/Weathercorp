import unittest
from main import loraSetup
import socket

class testMain(unittest.TestCase):
    def test_LoraSetup(self):
        print(self.assertIsInstance(loraSetup(),socket))