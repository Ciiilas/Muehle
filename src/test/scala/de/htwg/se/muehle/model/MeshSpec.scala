package de.htwg.se.muehle.model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec


class MeshSpec extends AnyWordSpec {
  "Mesh" should {
    val normal_mesh = new Mesh()
    val eol: String = sys.props("line.separator")
    "have a scalable bar" in {
      normal_mesh.bar(1, 1, true) should be("0─0" + eol)
      normal_mesh.bar(1,2, true) should be("0─0─0" + eol)
      normal_mesh.bar(1,1, false) should be("0─0")
      normal_mesh.bar(2, 1, true) should be("0──0" + eol)
      normal_mesh.bar(2, 2, true) should be("0──0──0" + eol)
      normal_mesh.bar(2, 1, false) should be("0──0")
    }
    "have scalable spacer" in {
      normal_mesh.spacer(1, 1, true) should be("│ │" + eol)
      normal_mesh.spacer(1, 2, true) should be("│ │ │" + eol)
      normal_mesh.spacer(1, 1, false) should be("│ │")
      normal_mesh.spacer(2, 1, true) should be("│  │" + eol)
      normal_mesh.spacer(2, 2, true) should be("│  │  │" + eol)
      normal_mesh.spacer(2, 1, false) should be("│  │")
    }
    "have a scalable middelBar" in {
      normal_mesh.middelBar(1) should be("0──0   0──0" + eol)
      normal_mesh.middelBar(2) should be("0──0──0   0──0──0" + eol)
      normal_mesh.middelBar(3) should be("0──0──0──0   0──0──0──0" + eol)
    }
    "have a scalable tinyBarFront" in {
      normal_mesh.tinyBarFront(1) should be("│  ")
      normal_mesh.tinyBarFront(2) should be("│  │  ")
      normal_mesh.tinyBarFront(3) should be("│  │  │  ")
    }
    "have a scalable tinyBarBack" in {
      normal_mesh.tinyBarBack(1, true) should be("  │" + eol)
      normal_mesh.tinyBarBack(2, true) should be("  │  │" + eol)
      normal_mesh.tinyBarBack(1, false) should be("  │")
    }
    "have a scalable mesh" in {
      normal_mesh.mesh(7, 2) should be("0───────0───────0\n│       │       │\n│       │       │\n│  0────0────0  │\n│  │    │    │  │\n│  │    │    │  │\n│  │  0─0─0  │  │\n│  │  │   │  │  │\n0──0──0   0──0──0\n│  │  │   │  │  │\n│  │  0─0─0  │  │\n│  │    │    │  │\n│  │    │    │  │\n│  0────0────0  │\n│       │       │\n│       │       │\n0───────0───────0")
      
    }

  }
}

