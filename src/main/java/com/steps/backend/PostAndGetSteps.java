package com.steps.backend;

import java.io.*;
import java.net.*;

import junit.framework.Assert;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;

public class PostAndGetSteps {
	private HttpURLConnection conn = null;

	/**
	 * Make connection to host
	 * 
	 * @param arg1
	 * @throws IOException
	 */
	@Given("^a url connection \"([^\"]*)\"$")
	public void MakeConnection(String arg1) throws IOException {

		if (this.conn != null)
			this.conn.disconnect();

		URL url = new URL(arg1);
		this.conn = (HttpURLConnection) url.openConnection();
	}

	/**
	 * Verify the response code
	 * 
	 * @param desiredResponseCode
	 * @throws IOException
	 */
	@Given("^I should get response code (\\d+)$")
	public void HTTPGetInterogationServerResponse(int desiredResponseCode)
			throws IOException {
		System.out.println("Server response:  " + conn.getHeaderField(0));
		Assert.assertTrue(conn.getHeaderField(0).contains(
				String.valueOf(desiredResponseCode)));
	}

	/**
	 * Receive and output the received message after the GET interrogation
	 * 
	 * @throws IOException
	 */
	@Then("^I make get interogation and view server message$")
	public void HTTPGetInterogationServerMessage() throws IOException {
		InputStream is = conn.getInputStream();
		int b;
		while ((b = is.read()) != -1) {
			System.out.write(b);
		}
	}

	/**
	 * Login with user name and password sent as String parameter to url using POST
	 * Interrogation
	 * 
	 * @param username
	 * @param userpass
	 * @throws IOException
	 */
	@And("^I make post message with user: \"([^\"]*)\" and password: \"([^\"]*)\"$")
	public void HttpPostForm(String username, String userpass)
			throws IOException {

		URL url = new URL("http://dippy.trei.ro");

		HttpURLConnection hConnection = (HttpURLConnection) url
				.openConnection();
		HttpURLConnection.setFollowRedirects(true);

		hConnection.setDoOutput(true);
		hConnection.setRequestMethod("POST");

		PrintStream ps = new PrintStream(hConnection.getOutputStream());
		ps.print("user=" + username + "&amp;pass=" + userpass
				+ ";do_login=Login");
		ps.close();

		hConnection.connect();

		if (HttpURLConnection.HTTP_OK == hConnection.getResponseCode()) {
			InputStream is = hConnection.getInputStream();

			System.out.println("!!! encoding: "
					+ hConnection.getContentEncoding());
			System.out.println("!!! message: "
					+ hConnection.getResponseMessage());

			is.close();
			hConnection.disconnect();
		}
	}

}
