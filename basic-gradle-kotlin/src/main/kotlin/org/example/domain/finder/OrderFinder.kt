package org.example.domain.finder

import io.ebean.Finder
import org.example.domain.Order

open class OrderFinder : Finder<Long, Order>(Order::class.java)
