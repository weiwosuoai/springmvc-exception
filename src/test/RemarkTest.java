
import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;
import org.junit.Test;
import site.exception.utils.TranslateUtil;

/**
 * remark 测试
 * Created by Allen on 2017/8/24.
 */
public class RemarkTest {

	@Test
	public void testHtml2Md() {
		Remark remark = new Remark();
		String htmlInput = "<h1>JavaScript Closures for Beginners</h1>\n" +
				"\n" +
				"<blockquote>Submitted by Morris on Tue, 2006-02-21 10:19.  Community-edited since.</blockquote>\n" +
				"\n" +
				"<h2>Closures Are Not Magic</h2>\n" +
				"\n" +
				"<p>This page explains closures so that a programmer can understand them — using working JavaScript code. It is not for gurus or functional programmers.</p>\n" +
				"\n" +
				"<p>Closures are <em>not hard</em> to understand once the core concept is grokked. However, they are impossible to understand by reading any academic papers or academically oriented information about them!</p>\n" +
				"\n" +
				"<p>This article is intended for programmers with some programming experience in a mainstream language, and who can read the following JavaScript function:</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pln\"> sayHello</span><span class=\"pun\">(</span><span class=\"pln\">name</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> text </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">'Hello '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> name</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> say </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">text</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"  say</span><span class=\"pun\">();</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"sayHello</span><span class=\"pun\">(</span><span class=\"str\">'Joe'</span><span class=\"pun\">);</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"45845057-5f33-7dd8-5031-c781f364487c\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<h2>An Example of a Closure</h2>\n" +
				"\n" +
				"<p>Two one sentence summaries:</p>\n" +
				"\n" +
				"<ul>\n" +
				"<li><p>A closure is one way of supporting <a href=\"https://en.wikipedia.org/wiki/First-class_function\" rel=\"noreferrer\">first-class functions</a>; it is an expression that can reference variables within its scope (when it was first declared), be assigned to a variable, be passed as an argument to a function, or be returned as a function result. </p></li>\n" +
				"<li><p>Or, a closure is a stack frame which is allocated when a function starts its execution, and <em>not freed</em> after the function returns (as if a 'stack frame' were allocated on the heap rather than the stack!).</p></li>\n" +
				"</ul>\n" +
				"\n" +
				"<p>The following code returns a reference to a function:</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pln\"> sayHello2</span><span class=\"pun\">(</span><span class=\"pln\">name</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> text </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">'Hello '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> name</span><span class=\"pun\">;</span><span class=\"pln\"> </span><span class=\"com\">// Local variable</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> say </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">text</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">return</span><span class=\"pln\"> say</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"</span><span class=\"kwd\">var</span><span class=\"pln\"> say2 </span><span class=\"pun\">=</span><span class=\"pln\"> sayHello2</span><span class=\"pun\">(</span><span class=\"str\">'Bob'</span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"say2</span><span class=\"pun\">();</span><span class=\"pln\"> </span><span class=\"com\">// logs \"Hello Bob\"</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"e61c5505-19e7-c7bd-d3c4-af6a68fa613d\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<p>Most JavaScript programmers will understand how a reference to a function is returned to a variable (<code>say2</code>) in the above code. If you don't, then you need to look at that before you can learn closures. A programmer using C would think of the function as returning a pointer to a function, and that the variables <code>say</code> and <code>say2</code> were each a pointer to a function.</p>\n" +
				"\n" +
				"<p>There is a critical difference between a C pointer to a function and a JavaScript reference to a function. In JavaScript, you can think of a function reference variable as having both a pointer to a function <em>as well</em> as a hidden pointer to a closure.</p>\n" +
				"\n" +
				"<p>The above code has a closure because the anonymous function <code>function() { console.log(text); }</code> is declared <em>inside</em> another function, <code>sayHello2()</code> in this example. In JavaScript, if you use the <code>function</code> keyword inside another function, you are creating a closure.</p>\n" +
				"\n" +
				"<p>In C and most other common languages, <em>after</em> a function returns, all the local variables are no longer accessible because the stack-frame is destroyed.</p>\n" +
				"\n" +
				"<p>In JavaScript, if you declare a function within another function, then the local variables can remain accessible after returning from the function you called. This is demonstrated above, because we call the function <code>say2()</code> after we have returned from <code>sayHello2()</code>. Notice that the code that we call references the variable <code>text</code>, which was a <em>local variable</em> of the function <code>sayHello2()</code>.</p>\n" +
				"\n" +
				"<pre class=\"default prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">text</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\"> </span><span class=\"com\">// Output of say2.toString();</span></code></pre>\n" +
				"\n" +
				"<p>Looking at the output of <code>say2.toString()</code>, we can see that the code refers to the variable <code>text</code>. The anonymous function can reference <code>text</code> which holds the value <code>'Hello Bob'</code> because the local variables of <code>sayHello2()</code> are kept in a closure.</p>\n" +
				"\n" +
				"<p>The magic is that in JavaScript a function reference also has a secret reference to the closure it was created in — similar to how delegates are a method pointer plus a secret reference to an object.</p>\n" +
				"\n" +
				"<h2>More examples</h2>\n" +
				"\n" +
				"<p>For some reason, closures seem really hard to understand when you read about them, but when you see some examples it becomes clear how they work (it took me a while).\n" +
				"I recommend working through the examples carefully until you understand how they work. If you start using closures without fully understanding how they work, you would soon create some very weird bugs!</p>\n" +
				"\n" +
				"<h3>Example 3</h3>\n" +
				"\n" +
				"<p>This example shows that the local variables are not copied — they are kept by reference. It is kind of like keeping a stack-frame in memory when the outer function exits!</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pln\"> say667</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"  </span><span class=\"com\">// Local variable that ends up within closure</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> num </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"lit\">42</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> say </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">num</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"  num</span><span class=\"pun\">++;</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">return</span><span class=\"pln\"> say</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"</span><span class=\"kwd\">var</span><span class=\"pln\"> sayNumber </span><span class=\"pun\">=</span><span class=\"pln\"> say667</span><span class=\"pun\">();</span><span class=\"pln\">\n" +
				"sayNumber</span><span class=\"pun\">();</span><span class=\"pln\"> </span><span class=\"com\">// logs 43</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"4434358a-f514-e3aa-a5d7-7140eb5b0e5b\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<h3>Example 4</h3>\n" +
				"\n" +
				"<p>All three global functions have a common reference to the <em>same</em> closure because they are all declared within a single call to <code>setupSomeGlobals()</code>.</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">var</span><span class=\"pln\"> gLogNumber</span><span class=\"pun\">,</span><span class=\"pln\"> gIncreaseNumber</span><span class=\"pun\">,</span><span class=\"pln\"> gSetNumber</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"</span><span class=\"kwd\">function</span><span class=\"pln\"> setupSomeGlobals</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"  </span><span class=\"com\">// Local variable that ends up within closure</span><span class=\"pln\">\n" +
				"  </span><span class=\"kwd\">var</span><span class=\"pln\"> num </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"lit\">42</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"  </span><span class=\"com\">// Store some references to functions as global variables</span><span class=\"pln\">\n" +
				"  gLogNumber </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">num</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"  gIncreaseNumber </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> num</span><span class=\"pun\">++;</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"  gSetNumber </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">(</span><span class=\"pln\">x</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> num </span><span class=\"pun\">=</span><span class=\"pln\"> x</span><span class=\"pun\">;</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"\n" +
				"setupSomeGlobals</span><span class=\"pun\">();</span><span class=\"pln\">\n" +
				"gIncreaseNumber</span><span class=\"pun\">();</span><span class=\"pln\">\n" +
				"gLogNumber</span><span class=\"pun\">();</span><span class=\"pln\"> </span><span class=\"com\">// 43</span><span class=\"pln\">\n" +
				"gSetNumber</span><span class=\"pun\">(</span><span class=\"lit\">5</span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"gLogNumber</span><span class=\"pun\">();</span><span class=\"pln\"> </span><span class=\"com\">// 5</span><span class=\"pln\">\n" +
				"\n" +
				"</span><span class=\"kwd\">var</span><span class=\"pln\"> oldLog </span><span class=\"pun\">=</span><span class=\"pln\"> gLogNumber</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"\n" +
				"setupSomeGlobals</span><span class=\"pun\">();</span><span class=\"pln\">\n" +
				"gLogNumber</span><span class=\"pun\">();</span><span class=\"pln\"> </span><span class=\"com\">// 42</span><span class=\"pln\">\n" +
				"\n" +
				"oldLog</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"com\">// 5</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"32b0226a-33aa-8bd8-d754-cb2da60ae03a\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<p>The three functions have shared access to the same closure — the local variables of <code>setupSomeGlobals()</code> when the three functions were defined.</p>\n" +
				"\n" +
				"<p>Note that in the above example, if you call <code>setupSomeGlobals()</code> again, then a new closure (stack-frame!) is created. The old <code>gLogNumber</code>, <code>gIncreaseNumber</code>, <code>gSetNumber</code> variables are overwritten with <em>new</em> functions that have the new closure. (In JavaScript, whenever you declare a function inside another function, the inside function(s) is/are recreated again <em>each</em> time the outside function is called.)</p>\n" +
				"\n" +
				"<h3>Example 5</h3>\n" +
				"\n" +
				"<p>This one is a real gotcha for many people, so you need to understand it. Be very careful if you are defining a function within a loop: the local variables from the closure do not act as you might first think.</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pln\"> buildList</span><span class=\"pun\">(</span><span class=\"pln\">list</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> result </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[];</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">for</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"kwd\">var</span><span class=\"pln\"> i </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">;</span><span class=\"pln\"> i </span><span class=\"pun\">&lt;</span><span class=\"pln\"> list</span><span class=\"pun\">.</span><span class=\"pln\">length</span><span class=\"pun\">;</span><span class=\"pln\"> i</span><span class=\"pun\">++)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"        </span><span class=\"kwd\">var</span><span class=\"pln\"> item </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">'item'</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> i</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"        result</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">(</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">item </span><span class=\"pun\">+</span><span class=\"pln\"> </span><span class=\"str\">' '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> list</span><span class=\"pun\">[</span><span class=\"pln\">i</span><span class=\"pun\">])}</span><span class=\"pln\"> </span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"    </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">return</span><span class=\"pln\"> result</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"\n" +
				"</span><span class=\"kwd\">function</span><span class=\"pln\"> testList</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> fnlist </span><span class=\"pun\">=</span><span class=\"pln\"> buildList</span><span class=\"pun\">([</span><span class=\"lit\">1</span><span class=\"pun\">,</span><span class=\"lit\">2</span><span class=\"pun\">,</span><span class=\"lit\">3</span><span class=\"pun\">]);</span><span class=\"pln\">\n" +
				"    </span><span class=\"com\">// Using j only to help prevent confusion -- could use i.</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">for</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"kwd\">var</span><span class=\"pln\"> j </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"lit\">0</span><span class=\"pun\">;</span><span class=\"pln\"> j </span><span class=\"pun\">&lt;</span><span class=\"pln\"> fnlist</span><span class=\"pun\">.</span><span class=\"pln\">length</span><span class=\"pun\">;</span><span class=\"pln\"> j</span><span class=\"pun\">++)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"        fnlist</span><span class=\"pun\">[</span><span class=\"pln\">j</span><span class=\"pun\">]();</span><span class=\"pln\">\n" +
				"    </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"\n" +
				" testList</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"com\">//logs \"item2 undefined\" 3 times</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"c09b8270-a82f-8de4-3e9a-8e127ebbf212\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<p>The line <code>result.push( function() {console.log(item + ' ' + list[i])}</code> adds a reference to an anonymous function three times to the result array. If you are not so familiar with anonymous functions think of it like:</p>\n" +
				"\n" +
				"<pre class=\"default prettyprint prettyprinted\" style=\"\"><code><span class=\"pln\">pointer </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">item </span><span class=\"pun\">+</span><span class=\"pln\"> </span><span class=\"str\">' '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> list</span><span class=\"pun\">[</span><span class=\"pln\">i</span><span class=\"pun\">])};</span><span class=\"pln\">\n" +
				"result</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">(</span><span class=\"pln\">pointer</span><span class=\"pun\">);</span></code></pre>\n" +
				"\n" +
				"<p>Note that when you run the example, <code>\"item2 undefined\"</code> is alerted three times! This is because just like previous examples, there is only one closure for the local variables for <code>buildList</code>. When the anonymous functions are called on the line <code>fnlist[j]()</code>; they all use the same single closure, and they use the current value for <code>i</code> and <code>item</code> within that one closure (where <code>i</code> has a value of <code>3</code> because the loop had completed, and <code>item</code> has a value of <code>'item2'</code>). Note we are indexing from 0 hence <code>item</code> has a value of <code>item2</code>. And the i++ will increment <code>i</code> to the value <code>3</code>.</p>\n" +
				"\n" +
				"<h3>Example 6</h3>\n" +
				"\n" +
				"<p>This example shows that the closure contains any local variables that were declared inside the outer function before it exited. Note that the variable <code>alice</code> is actually declared after the anonymous function. The anonymous function is declared first; and when that function is called it can access the <code>alice</code> variable because <code>alice</code> is in the same scope (JavaScript does <a href=\"https://stackoverflow.com/a/3725763/1269037\">variable hoisting</a>).\n" +
				"Also <code>sayAlice()()</code> just directly calls the function reference returned from <code>sayAlice()</code> — it is exactly the same as what was done previously but without the temporary variable.</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pln\"> sayAlice</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> say </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\"> console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"pln\">alice</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"    </span><span class=\"com\">// Local variable that ends up within closure</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> alice </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"str\">'Hello Alice'</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">return</span><span class=\"pln\"> say</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"sayAlice</span><span class=\"pun\">()();</span><span class=\"com\">// logs \"Hello Alice\"</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"f2ec283c-0884-8321-2a5d-f17e6dd54944\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<p>Tricky: note also that the <code>say</code> variable is also inside the closure, and could be accessed by any other function that might be declared within <code>sayAlice()</code>, or it could be accessed recursively within the inside function.</p>\n" +
				"\n" +
				"<h3>Example 7</h3>\n" +
				"\n" +
				"<p>This final example shows that each call creates a separate closure for the local variables. There is <em>not</em> a single closure per function declaration. There is a closure for <em>each call</em> to a function.</p>\n" +
				"\n" +
				"<p></p><div class=\"snippet\" data-lang=\"js\" data-hide=\"false\" data-console=\"true\" data-babel=\"false\">\n" +
				"<div class=\"snippet-code\">\n" +
				"<pre class=\"snippet-code-js lang-js prettyprint prettyprinted\" style=\"\"><code><span class=\"kwd\">function</span><span class=\"pln\"> newClosure</span><span class=\"pun\">(</span><span class=\"pln\">someNum</span><span class=\"pun\">,</span><span class=\"pln\"> someRef</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"    </span><span class=\"com\">// Local variables that end up within closure</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> num </span><span class=\"pun\">=</span><span class=\"pln\"> someNum</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> anArray </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">[</span><span class=\"lit\">1</span><span class=\"pun\">,</span><span class=\"lit\">2</span><span class=\"pun\">,</span><span class=\"lit\">3</span><span class=\"pun\">];</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">var</span><span class=\"pln\"> ref </span><span class=\"pun\">=</span><span class=\"pln\"> someRef</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"    </span><span class=\"kwd\">return</span><span class=\"pln\"> </span><span class=\"kwd\">function</span><span class=\"pun\">(</span><span class=\"pln\">x</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">\n" +
				"        num </span><span class=\"pun\">+=</span><span class=\"pln\"> x</span><span class=\"pun\">;</span><span class=\"pln\">\n" +
				"        anArray</span><span class=\"pun\">.</span><span class=\"pln\">push</span><span class=\"pun\">(</span><span class=\"pln\">num</span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"        console</span><span class=\"pun\">.</span><span class=\"pln\">log</span><span class=\"pun\">(</span><span class=\"str\">'num: '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> num </span><span class=\"pun\">+</span><span class=\"pln\">\n" +
				"            </span><span class=\"str\">'; anArray: '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> anArray</span><span class=\"pun\">.</span><span class=\"pln\">toString</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\">\n" +
				"            </span><span class=\"str\">'; ref.someVar: '</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> ref</span><span class=\"pun\">.</span><span class=\"pln\">someVar </span><span class=\"pun\">+</span><span class=\"pln\"> </span><span class=\"str\">';'</span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"      </span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"</span><span class=\"pun\">}</span><span class=\"pln\">\n" +
				"obj </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"pun\">{</span><span class=\"pln\">someVar</span><span class=\"pun\">:</span><span class=\"pln\"> </span><span class=\"lit\">4</span><span class=\"pun\">};</span><span class=\"pln\">\n" +
				"fn1 </span><span class=\"pun\">=</span><span class=\"pln\"> newClosure</span><span class=\"pun\">(</span><span class=\"lit\">4</span><span class=\"pun\">,</span><span class=\"pln\"> obj</span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"fn2 </span><span class=\"pun\">=</span><span class=\"pln\"> newClosure</span><span class=\"pun\">(</span><span class=\"lit\">5</span><span class=\"pun\">,</span><span class=\"pln\"> obj</span><span class=\"pun\">);</span><span class=\"pln\">\n" +
				"fn1</span><span class=\"pun\">(</span><span class=\"lit\">1</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"com\">// num: 5; anArray: 1,2,3,5; ref.someVar: 4;</span><span class=\"pln\">\n" +
				"fn2</span><span class=\"pun\">(</span><span class=\"lit\">1</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"com\">// num: 6; anArray: 1,2,3,6; ref.someVar: 4;</span><span class=\"pln\">\n" +
				"obj</span><span class=\"pun\">.</span><span class=\"pln\">someVar</span><span class=\"pun\">++;</span><span class=\"pln\">\n" +
				"fn1</span><span class=\"pun\">(</span><span class=\"lit\">2</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"com\">// num: 7; anArray: 1,2,3,5,7; ref.someVar: 5;</span><span class=\"pln\">\n" +
				"fn2</span><span class=\"pun\">(</span><span class=\"lit\">2</span><span class=\"pun\">);</span><span class=\"pln\"> </span><span class=\"com\">// num: 8; anArray: 1,2,3,6,8; ref.someVar: 5;</span></code></pre>\n" +
				"<div class=\"snippet-result\"><div class=\"snippet-ctas\"><button type=\"button\"><span class=\"icon-play-white _hover\"></span> Run code snippet</button><input class=\"copySnippet btn-secondary\" type=\"button\" value=\"Copy snippet to answer\" style=\"display: none;\"><button type=\"button\" class=\"hideResults btn-clear\" style=\"display: none;\">Hide results</button><div class=\"popout-code\"><a class=\"snippet-expand-link\">Expand snippet</a></div></div><div class=\"snippet-result-code\" style=\"display: none;\"><iframe name=\"a6ca8b19-aeba-ff53-610c-c277a804e966\" sandbox=\"allow-forms allow-modals allow-scripts\" class=\"snippet-box-edit\" frameborder=\"0\"></iframe></div></div></div>\n" +
				"</div>\n" +
				"<p></p>\n" +
				"\n" +
				"<h2>Summary</h2>\n" +
				"\n" +
				"<p>If everything seems completely unclear then the best thing to do is to play with the examples. Reading an explanation is much harder than understanding examples.\n" +
				"My explanations of closures and stack-frames, etc. are not technically correct — they are gross simplifications intended to help to understand. Once the basic idea is grokked, you can pick up the details later.</p>\n" +
				"\n" +
				"<h2>Final points:</h2>\n" +
				"\n" +
				"<ul>\n" +
				"<li>Whenever you use <code>function</code> inside another function, a closure is used.</li>\n" +
				"<li>Whenever you use <code>eval()</code> inside a function, a closure is used. The text you <code>eval</code> can reference local variables of the function, and within <code>eval</code> you can even create new local variables by using <code>eval('var foo = …')</code></li>\n" +
				"<li>When you use <code>new Function(…)</code> (the <a href=\"https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function\" rel=\"noreferrer\">Function constructor</a>) inside a function, it does not create a closure. (The new function cannot reference the local variables of the outer function.)</li>\n" +
				"<li>A closure in JavaScript is like keeping a copy of all the local variables, just as they were when a function exited.</li>\n" +
				"<li>It is probably best to think that a closure is always created just an entry to a function, and the local variables are added to that closure.</li>\n" +
				"<li>A new set of local variables is kept every time a function with a closure is called (given that the function contains a function declaration inside it, and a reference to that inside function is either returned or an external reference is kept for it in some way).</li>\n" +
				"<li>Two functions might look like they have the same source text, but have completely different behaviour because of their 'hidden' closure. I don't think JavaScript code can actually find out if a function reference has a closure or not.</li>\n" +
				"<li>If you are trying to do any dynamic source code modifications (for example: <code>myFunction = Function(myFunction.toString().replace(/Hello/,'Hola'));</code>), it won't work if <code>myFunction</code> is a closure (of course, you would never even think of doing source code string substitution at runtime, but...).</li>\n" +
				"<li>It is possible to get function declarations within function declarations within functions — and you can get closures at more than one level.</li>\n" +
				"<li>I think normally a closure is the term for both the function along with the variables that are captured. Note that I do not use that definition in this article!</li>\n" +
				"<li>I suspect that closures in JavaScript differ from those normally found in functional languages.</li>\n" +
				"</ul>\n" +
				"\n" +
				"<h2>Links</h2>\n" +
				"\n" +
				"<ul>\n" +
				"<li>Douglas Crockford's simulated <a href=\"http://www.crockford.com/javascript/private.html\" rel=\"noreferrer\">private attributes and private methods</a> for an object, using closures.</li>\n" +
				"<li>A great explanation of how closures can <a href=\"https://www.codeproject.com/Articles/12231/Memory-Leakage-in-Internet-Explorer-revisited\" rel=\"noreferrer\">cause memory leaks in IE</a> if you are not careful.</li>\n" +
				"</ul>\n" +
				"\n" +
				"<h2>Thanks</h2>\n" +
				"\n" +
				"<p>If you have <em>just</em> learned closures (here or elsewhere!), then I am interested in any feedback from you about any changes you might suggest that could make this article clearer. Send an email to morrisjohns.com (morris_closure @). Please note that I am not a guru on JavaScript — nor on closures.</p>\n" +
				"\n" +
				"<hr>\n" +
				"\n" +
				"<p>Original post by Morris can be found in the <a href=\"http://web.archive.org/web/20080209105120/http:/blog.morrisjohns.com/javascript_closures_for_dummies\" rel=\"noreferrer\">Internet Archive</a>.</p>";
		String markdown = remark.convertFragment(htmlInput);
		System.out.println(markdown);

//		System.out.println(TranslateUtil.translateStackoverflowText(markdown));
	}
}
