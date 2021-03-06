/*
 * Copyright 2001-2008 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalatest

/**
 * <strong>This trait has been deprecated and will be removed in a future version of ScalaTest.
 * If you are only using its <code>beforeEach</code> and/or <code>afterEach</code> methods,
 * mix in <code>BeforeAndAfterEach</code> instead. If you are only using its <code>beforeAll</code>
 * and/or <code>afterAll</code> methods, mix in <code>BeforeAndAfterAll</code> instead.
 * If you are using at least one "each" and one "all" method, mix in
 * <code>BeforeAndAfterEach with BeforeAndAfterAll</code> instead.</strong>
 *
 * @author Bill Venners
 */
@deprecated // deprecated in 1.0, so remove in 1.4
trait BeforeAndAfter extends BeforeAndAfterEach with BeforeAndAfterAll {
  this: Suite =>
}
