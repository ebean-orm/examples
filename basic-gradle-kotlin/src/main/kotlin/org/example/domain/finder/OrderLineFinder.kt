package org.example.domain.finder

import io.ebean.Finder
import org.example.domain.OrderLine

open class OrderLineFinder : Finder<Long, OrderLine>(OrderLine::class.java)

