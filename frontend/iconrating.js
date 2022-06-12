import { LitElement, html, css } from 'lit-element';
import { repeat } from 'lit/directives/repeat';

/**
 * `icons-rating`
 * Icons Rating
 *
 * @customElement
 * @polymer
 * @demo demo/index.html
 */
class IconsRating extends LitElement {
    static get properties() {
        return {
            _icons: {
                type: Array
            },
            numicons: {
                type: Number,
                value: 7
            },
            rating: {
                type: Number,
                value: 0
            },
            manual: {
                type: Boolean,
                value: false
            },
            mode: {
                type: String,
                value: 'auto'
            },
            resetbtn: {
                type: Boolean,
                value: false
            }
        }
    }

    static get styles() {
        return css`
      :host(:not([hidden])) {
          display: block;
          font-size: 2em;
          --icon-size: 40px;
          --icon-color: #00FFFF;
        }
        fieldset,
        label {
          margin: 0;
          padding: 0;
        }
        fieldset {
          border: none;
        }
        input {
          display: none;
        }
        label:before {
          font-size: var(--icon-size, 1em);
          display: inline-block;
          content: var(--start-unicode, "⛷");
        }
        label {
          color: var(--icon-color);
          opacity: 0.3;
        }
        label[data-hightlight] {
          opacity: 1;
        }
        label.resetbtn {
          color: #F00;
          font-size:0.5em;
          opacity: 1;
        }
        label.resetbtn:before {
          font-size: var(--icon-size, 1em);
          display: inline-block;
          content: var(--start-unicode, "ø");
          opacity:1;
        }
    `;
    }

    constructor() {
        super();
        this._rate = this._rate.bind(this);
    }

    connectedCallback() {
        super.connectedCallback();
        this._updateNumicons();
    }

    disconnectedCallback() {
        super.disconnectedCallback();

        const ratingElement = this.renderRoot.querySelector('#rating')
        if(ratingElement) ratingElement.removeEventListener('click', this._rate);
    }

    updated(changedProperties, o) {
        if (changedProperties.get('manual') !== this.manual) {
            this._manualChanged();
        }
        if (changedProperties.get('rating') !==  this.rating) {
            this._ratingChange();
        }
    }

    _updateNumicons() {
        this._icons = new Array(this.numicons);
    }

    _isHightlight(index) {
        return index < this.rating;
    }

    _ratingChange() {
        if (this.rating < 0) {
            this.rating = 0;
        } else if (this.rating > this.numicons) {
            this.rating = this.numicons;
        }
        this.dispatchEvent(new CustomEvent('rating-changed', { detail: this.rating }));
    }

    reset() {
        this.rating = 0;
    }

    _manualChanged() {
        if (this.manual) {
            this.renderRoot.querySelector('#rating').addEventListener('click', this._rate);
        } else {
            this.renderRoot.querySelector('#rating').removeEventListener('click', this._rate);
        }
    }

    _rate(ev) {
        if (ev.target.nodeName === 'INPUT') {
            this.rating = parseInt(ev.target.value) + 1;
        }
    }

    render() {
        return html`
      <fieldset id="rating">
        ${(this.resetbtn && this.manual) ? html`<label class="resetbtn"><input @click="${this.reset}" type="radio" id="x" name="resetbtn" value="-1" /></label>  ` : html``}
        ${repeat(
            this._icons,
            item => item,
            (item, i) => html`<label for="icon${i}" ?data-hightlight="${this._isHightlight(i)}">
                              <input type="radio" id="icon${i}" name="rating" value="${i}" />
                            </label>`
        )}
      </fieldset>
    `;
    }
}

window.customElements.define('icons-rating', IconsRating);